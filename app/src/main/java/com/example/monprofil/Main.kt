package com.example.monprofil

import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Locale.filter
/*
open class Piece(val largeur: Float, val longueur: Float, val nom: String){
    fun surface(): Int {
        var result = 0;
        result = (longueur * largeur).toInt();

        return result;
    }
}
/*
class Cuisine(largeur: Float, longueur: Float, nom: String) : Piece(largeur, longueur, nom){

}

class Salon(largeur: Float, longueur: Float, nom: String) : Piece(largeur, longueur, nom){


}

val list = MutableStateFlow<List<Cuisine>>(emptyList())

val list2 = MutableStateFlow<List<Salon>>(emptyList())

fun parcourslist(){

}*/

class Etudiant( val name: String, val promo: String, val matieres: List<String>)

val etudiants = listOf(
    Etudiant("Paul", "2025", listOf("mobile","web", "BDD")),
    Etudiant("Yazid","2024", listOf("mobile","Android","Réseau")),
    Etudiant("Caroline","2025", listOf("SE","Anglais")),
)




fun main(){
    etudiants.filter { it.promo == "épice"}.forEach { println(it.name) }
    etudiants.groupBy { it.promo }.forEach { k,v -> println("clé: $k, ${v.map { it.name}}") }
   /* println(etudiants.sumOf { it.matieres })*/
}
*/