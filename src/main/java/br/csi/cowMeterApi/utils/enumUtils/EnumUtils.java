package br.csi.cowMeterApi.utils.enumUtils;

import br.csi.cowMeterApi.models.Role;

public class EnumUtils {
    public static boolean isRoleValid(String roleString) {
        for (Role role : Role.values()) {
            if (role.name().equals(roleString)) {
                return true;
            }
        }
        return false;
    }
}
