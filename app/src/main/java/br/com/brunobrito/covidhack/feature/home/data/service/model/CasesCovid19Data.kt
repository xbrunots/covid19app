import java.io.Serializable

data class CasesCovid19Data(
    val data: List<CasesCovid19> = listOf()
) : Serializable

data class CasesCovid19(
    val uid: Int? = 0,
    val uf: String? = "",
    val state: String? = "",
    val cases: Int? = 0,
    val deaths: Int? = 0,
    val suspects: Int? = 0,
    val refuses: Int? = 0,
    val datetime: String? = ""
) : Serializable

data class CasesCovid19Resume(
    var lastCases: List<CasesCovid19> = listOf(),
    var nowCases: List<CasesCovid19> = listOf()
) : Serializable