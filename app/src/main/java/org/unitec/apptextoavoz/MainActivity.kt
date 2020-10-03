package org.unitec.apptextoavoz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
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

        //KEMOZION OwO
        Timer("Bienvenida UwU",false).schedule(1000){
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
}