import {Component, OnInit} from '@angular/core';
import {RoleService} from "../role.service";
import {Router} from "@angular/router";
import {Role} from "../../models/Role";

@Component({
  selector: 'app-role-list',
  standalone: true,
  imports: [],
  templateUrl: './role-list.component.html',
  styleUrl: './role-list.component.css'
})
export class RoleListComponent implements OnInit {

  roles: Role[] = [];

  constructor(private service: RoleService,
              private router: Router) {
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

}
