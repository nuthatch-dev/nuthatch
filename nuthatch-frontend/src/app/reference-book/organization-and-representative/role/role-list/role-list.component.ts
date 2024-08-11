import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {RoleService} from "../role.service";
import {Role} from "../../models/Role";
import {NgTemplateOutlet} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-role-list',
  standalone: true,
  imports: [
    NgTemplateOutlet,
    FormsModule
  ],
  templateUrl: './role-list.component.html',
  styleUrl: './role-list.component.css'
})
export class RoleListComponent implements OnInit {

  /*
  Шаблоны
   */
  @ViewChild("readTemplate", {static: false}) readTemplate!: TemplateRef<any>;
  @ViewChild("editTemplate", {static: false}) editTemplate!: TemplateRef<any>;

  roles: Role[] = [];

  constructor(private service: RoleService) {
  }

  ngOnInit(): void {
    this.getAllRoles();
  }

  private getAllRoles() {
    this.service.getAllRoles().subscribe({
      next: value => {
        this.roles = value;
      },
      error: err => console.log(err)
    });
  }

  editedRole: Role = {
    uuid: "",
    roleName: "",
  };
  private setEmptyRole() {
    this.editedRole = {
      uuid: "",
      roleName: "",
    };
  }
  entityIsCreated: boolean = false;

  addRole() {
    this.editedRole = {
      uuid: "",
      roleName: "",
    };
    this.roles.push(this.editedRole);
    this.entityIsCreated = true;
  }

  editRole(role: Role) {
    this.editedRole = {
      uuid: role.uuid,
      roleName: role.roleName,
    };
  }

  /*
  Загрузка шаблона
   */
  loadTemplate(role: Role) {
    if (this.editedRole && this.editedRole.uuid == role.uuid) {
      return this.editTemplate;
    } else {
      return this.readTemplate;
    }
  }

  saveRole() {
    if (this.entityIsCreated) {
      this.service.createRole(this.editedRole).subscribe({
        next: _ => {
          this.setEmptyRole();
          this.entityIsCreated = false;
          this.getAllRoles();
        },
        error: err => console.log(err)
      });
    } else {
      this.service.updateRole(this.editedRole).subscribe({
        next: _ => {
          this.setEmptyRole();
          this.getAllRoles();
        },
        error: err => console.log(err)
      });
    }
  }

  cancelCreating() {
    if (this.entityIsCreated) {
      this.roles.pop();
      this.entityIsCreated = false;
    }
    this.setEmptyRole();
  }

  deletedRoleUuid: string = "";

  deleteRole() {
    this.service.deleteById(this.deletedRoleUuid).subscribe({
      next: _ => {
        this.getAllRoles();
      },
      error: err => console.log(err)
    });
  }

  cancelDeleting() {
    this.deletedRoleUuid = "";
  }

}
