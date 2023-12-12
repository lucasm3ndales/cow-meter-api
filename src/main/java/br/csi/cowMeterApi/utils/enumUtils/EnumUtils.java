package br.csi.cowMeterApi.utils.enumUtils;


import br.csi.cowMeterApi.models.Usuario;

public class EnumUtils {
    public static <T extends Enum<T>> T stringToEnum(Class<T> enumClass, String value) {
        T enumArr = null;
        for (T enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equalsIgnoreCase(value)) {
                enumArr = enumValue;
            }
        }
        return enumArr;
    }
}
