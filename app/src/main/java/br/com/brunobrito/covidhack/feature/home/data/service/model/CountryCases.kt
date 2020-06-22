import java.io.Serializable

data class CountryCasesData(
    val data: CountryCases
) : Serializable


data class CountryCases(
    val country: String? = "",
    val cases: Int? = 0,
    val confirmed: Int? = 0,
    val deaths: Int? = 0,
    val recovered: Int? = 0,
    val updated_at: String? = ""
) : Serializable