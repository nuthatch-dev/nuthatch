import {RoleMap} from "./RoleMap"
import {CommonEntity} from "../../../models/representative/CommonEntity";

/**
 * Управление ролями ЮЛ/ИП/ФЛ
 */
export abstract class RoleManagement <E extends CommonEntity> {

  entity: E | null = null;

  /*
  Map-ы ролей определенных для сущности и доступных к добавлению.
   */
  protected readonly Array = Array;
  protected readonly RoleMap = RoleMap;
  freeRoleMap = new Map<string, string>();
  assignedRoleMap = new Map<string, string>();

  protected prepareRoleMaps() {
    this.freeRoleMap = new Map<string, string>();
    this.assignedRoleMap = new Map<string, string>();
    RoleMap.forEach((value, key) => {
      if (this.entity!.roleSet.indexOf(key) == -1) {
        this.freeRoleMap.set(key, value);
      } else {
        this.assignedRoleMap.set(key, value);
      }
    });
  }

  /*
  Изменение Map-ов ролей по действиям пользователя
   */
  addRoleClick(key: string) {
    this.freeRoleMap.delete(key);
    this.assignedRoleMap.set(key, <string>RoleMap.get(key));
  }

  removeRoleClick(key: string) {
    this.assignedRoleMap.delete(key);
    this.freeRoleMap.set(key, <string>RoleMap.get(key));
  }
}
