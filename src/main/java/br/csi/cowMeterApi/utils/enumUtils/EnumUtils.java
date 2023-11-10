package br.csi.cowMeterApi.utils.enumUtils;


import br.csi.cowMeterApi.models.Usuario;

public class EnumUtils {
    public static boolean isRoleValid(String roleString) {
        System.out.println(roleString);
        for (Usuario.Role role : Usuario.Role.values()) {
            if (role.name().equals(roleString)) {
                return true;
            }
        }
        return false;
    }
}
