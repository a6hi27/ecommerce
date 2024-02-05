import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'eca-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private router:Router){
  }
  public redirectToLogin(){
    this.router.navigate(['/login'])
  }
  public redirectToRegister(){
    this.router.navigate(['/register'])
  }
}