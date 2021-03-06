package fr.geonature.occtax.settings

import android.os.Parcel
import android.os.Parcelable
import fr.geonature.commons.settings.IAppSettings
import fr.geonature.maps.settings.MapSettings

/**
 * Global internal settings.
 *
 * @author [S. Grimault](mailto:sebastien.grimault@gmail.com)
 */
data class AppSettings(
    var areaObservationDuration: Int = DEFAULT_AREA_OBSERVATION_DURATION,
    var mapSettings: MapSettings? = null,
    var nomenclatureSettings: NomenclatureSettings? = null
) : IAppSettings {

    private constructor(source: Parcel) : this(
        source.readInt(),
        source.readParcelable(MapSettings::class.java.classLoader) as MapSettings?,
        source.readParcelable(NomenclatureSettings::class.java.classLoader) as NomenclatureSettings?
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(
        dest: Parcel?,
        flags: Int
    ) {
        dest?.also {
            it.writeInt(areaObservationDuration)
            it.writeParcelable(
                mapSettings,
                0
            )
            it.writeParcelable(
                nomenclatureSettings,
                0
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AppSettings) return false

        if (areaObservationDuration != other.areaObservationDuration) return false
        if (mapSettings != other.mapSettings) return false
        if (nomenclatureSettings != other.nomenclatureSettings) return false

        return true
    }

    override fun hashCode(): Int {
        var result = areaObservationDuration
        result = 31 * result + (mapSettings?.hashCode() ?: 0)
        result = 31 * result + (nomenclatureSettings?.hashCode() ?: 0)

        return result
    }

    companion object {
        const val DEFAULT_AREA_OBSERVATION_DURATION = 365

        @JvmField
        val CREATOR: Parcelable.Creator<AppSettings> = object : Parcelable.Creator<AppSettings> {
            override fun createFromParcel(parcel: Parcel): AppSettings {
                return AppSettings(parcel)
            }

            override fun newArray(size: Int): Array<AppSettings?> {
                return arrayOfNulls(size)
            }
        }
    }
}
