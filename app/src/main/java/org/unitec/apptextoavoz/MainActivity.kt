package org.unitec.apptextoavoz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    // este objeto es el intermediario entre la app y el text to speech

    private var TTS:TextToSpeech?=null
    private val codigo_bajo_peticion=100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TTS= TextToSpeech(this,this)

        Hablar.setOnClickListener {
            val intent = Intent (RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            try {
                startActivityForResult(intent,codigo_bajo_peticion)
            } catch(e:Exception){ }
        }

        FraseEscrita.setOnClickListener{
            if (FraseEscrita.text.isEmpty()){
                Toast.makeText(this, "Debes escribir para que hable, pendejo",Toast.LENGTH_LONG).show()
            }
            else{
                hablarTexto(FraseEscrita.text.toString())
            }
        }

        //KEMOZION OwO
        Timer("Bienvenida UwU",false).schedule(3000){
            TTS!!.speak(
                    "Hola, pinche putita, te pones bien cachonda hija de tu puta madre. Ya no aguanto más Elizabeth",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    ""
            )
        }
    }

    override fun onInit(status: Int) {
        //SIRVE PARA INICIALIZAR LA CONFIGURACIÓN PARA ARRANCAR LA APP
        if(status==TextToSpeech.SUCCESS){
            //SI EL IF SE CUMPLE, SIGUE LA EJECUCIÓN AQUÍ MERO
            var local=Locale("spa","MEX")
            //LA SIGUIENTE VARIABLE ES PARA QUE INTERNAMENTE SEPAMOS QUE LA APP VA BIEN
            val resultado=TTS!!.setLanguage(local)
            if(resultado==TextToSpeech.LANG_MISSING_DATA){
                Log.i("MALO","NO SIRVE, WEY UnU")
            }
        }
    }
    fun hablarTexto(textoHablar:String){
        TTS!!.speak(textoHablar,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        super.onDestroy()
        if(TTS != null){
            TTS!!.stop()
            TTS!!.shutdown()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            codigo_bajo_peticion->{
                if(resultCode== RESULT_OK && null!=data){
                    val result=data.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)
                    TextoInterpretado.setText(result!![0])
                }
            }
        }
    }
}