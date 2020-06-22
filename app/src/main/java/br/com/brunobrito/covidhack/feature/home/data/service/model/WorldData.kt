package br.com.brunobrito.covidhack.feature.home.data.service.model


data class WorldData (
    val data: List<Datum>? = null
)

data class Datum (
    val name: String? = null,
    val topLevelDomain: List<String>? = null,
    val alpha2Code: String? = null,
    val alpha3Code: String? = null,
    val callingCodes: List<String>? = null,
    val capital: String? = null,
    val altSpellings: List<String>? = null,
    val region: Region? = null,
    val subregion: String? = null,
    val population: Long? = null,
    val latlng: List<Double>? = null,
    val demonym: String? = null,
    val area: Double? = null,
    val gini: Double? = null,
    val timezones: List<String>? = null,
    val borders: List<String>? = null,
    val nativeName: String? = null,
    val numericCode: String? = null,
    val currencies: List<Currency>? = null,
    val languages: List<Language>? = null,
    val translations: Translations? = null,
    val flag: String? = null,
    val regionalBlocs: List<RegionalBloc>? = null,
    val cioc: String? = null
)

data class Currency (
    val code: String? = null,
    val name: String? = null,
    val symbol: String? = null
)

data class Language (
    val iso6391: String? = null,
    val iso6392: String? = null,
    val name: String? = null,
    val nativeName: String? = null
)

enum class Region {
    Africa,
    Americas,
    Asia,
    Empty,
    Europe,
    Oceania,
    Polar
}

data class RegionalBloc (
    val acronym: Acronym? = null,
    val name: Name? = null,
    val otherAcronyms: List<OtherAcronym>? = null,
    val otherNames: List<OtherName>? = null
)

enum class Acronym {
    Al,
    Asean,
    Au,
    Cais,
    Caricom,
    Cefta,
    Eeu,
    Efta,
    Eu,
    Nafta,
    Pa,
    Saarc,
    Usan
}

enum class Name {
    AfricanUnion,
    ArabLeague,
    AssociationOfSoutheastAsianNations,
    CaribbeanCommunity,
    CentralAmericanIntegrationSystem,
    CentralEuropeanFreeTradeAgreement,
    EurasianEconomicUnion,
    EuropeanFreeTradeAssociation,
    EuropeanUnion,
    NorthAmericanFreeTradeAgreement,
    PacificAlliance,
    SouthAsianAssociationForRegionalCooperation,
    UnionOfSouthAmericanNations
}

enum class OtherAcronym {
    Eaeu,
    Sica,
    Unasul,
    Unasur,
    Uzan
}

enum class OtherName {
    AccordDeLibreÉchangeNordAméricain,
    AlianzaDelPacífico,
    CaribischeGemeenschap,
    CommunautéCaribéenne,
    ComunidadDelCaribe,
    JāmiAtAdDuwalAlArabīyah,
    LeagueOfArabStates,
    SistemaDeLaIntegraciónCentroamericana,
    SouthAmericanUnion,
    TratadoDeLibreComercioDeAméricaDelNorte,
    UmojaWaAfrika,
    UnieVanZuidAmerikaanseNaties,
    UnionAfricaine,
    UniãoAfricana,
    UniãoDeNaçõesSulAmericanas,
    UniónAfricana,
    UniónDeNacionesSuramericanas,
    الاتحادالأفريقي,
    جامعةالدولالعربية
}

data class Translations (
    val de: String? = null,
    val es: String? = null,
    val fr: String? = null,
    val ja: String? = null,
    val it: String? = null,
    val br: String? = null,
    val pt: String? = null,
    val nl: String? = null,
    val hr: String? = null,
    val fa: String? = null
)
