package fr.nicolaslinard.mockemon.dto

import fr.nicolaslinard.mockemon.model.LocalizedName
import fr.nicolaslinard.mockemon.model.Mockemon
import fr.nicolaslinard.mockemon.model.MockemonBase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PokemonDTO(
    val id: Int,
    val name: NameData,
    val type: List<String>,
    val base: BaseData
)

@Serializable
data class BaseData (
    @SerialName("HP")
    val hp: Int,
    @SerialName("Attack")
    val attack: Int,
    @SerialName("Defense")
    val defense: Int,
    @SerialName("Sp. Attack")
    val spAttack: Int,
    @SerialName("Sp. Defense")
    val spDefense: Int,
    @SerialName("Speed")
    val speed: Int
)


@Serializable
data class NameData(
    val english: String,
    val japanese: String,
    val chinese: String,
    val french: String
)

fun PokemonDTO.toMockemon(): Mockemon {
    return Mockemon(
        time = LocalDateTime.now(),
        id = this.id,
        name = LocalizedName(
            english = this.name.english,
            japanese = this.name.japanese,
            chinese = this.name.chinese,
            french = this.name.french
        ),
        type = this.type,
        base = MockemonBase(
            hP = this.base.hp,
            attack = this.base.attack,
            defense = this.base.defense,
            spAttack = this.base.spAttack,
            spDefense = this.base.spDefense,
            speed = this.base.speed
        )
    )
}

