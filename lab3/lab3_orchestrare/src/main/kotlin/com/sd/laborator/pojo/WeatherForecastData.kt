package com.sd.laborator.pojo
enum class WeatherState(val code: Int, val description: String) {
    CLEAR_SKY(0, "Cer senin"),
    MAINLY_CLEAR(1, "Preponderent senin"),
    PARTLY_CLOUDY(2, "Parțial noros"),
    OVERCAST(3, "Noros / Închis"),
    FOG(45, "Ceață"),
    DEPOSITING_RIME_FOG(48, "Ceață cu chiciură"),
    DRIZZLE_LIGHT(51, "Burniță ușoară"),
    DRIZZLE_MODERATE(53, "Burniță moderată"),
    DRIZZLE_DENSE(55, "Burniță densă"),
    RAIN_SLIGHT(61, "Ploaie slabă"),
    RAIN_MODERATE(63, "Ploaie moderată"),
    RAIN_HEAVY(65, "Ploaie puternică"),
    SNOW_FALL_SLIGHT(71, "Ninsoare slabă"),
    SNOW_FALL_MODERATE(73, "Ninsoare moderată"),
    SNOW_FALL_HEAVY(75, "Ninsoare puternică"),
    THUNDERSTORM(95, "Furtună"),
    THUNDERSTORM_HAIL(99, "Furtună cu grindină"),
    UNKNOWN(-1, "Cod necunoscut");
    companion object {
        private val map = entries.associateBy(WeatherState::code)
        fun fromCode(code: Int): WeatherState = map[code] ?: UNKNOWN
    }
}
//data class obligatorii trb sa aiba constructor primar
//obiectele POJO  NU proceseaza date!!
data class WeatherForecastData(
    var location: String,
    var date: String,
    var weatherState: WeatherState,
    var windDirection: String,
    var windSpeed: Int, // km/h
    var minTemp: Int, // grade celsius
    var maxTemp: Int,
    var currentTemp: Int,
    var humidity: Int // procent
)