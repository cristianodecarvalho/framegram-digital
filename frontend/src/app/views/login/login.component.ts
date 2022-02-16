import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from 'src/app/models/Auth.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  auth: Auth = {
    login: '',
    password: ''
  }

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  authenticate(): void {
    this.authService.authenticate(this.auth).subscribe( res => {
      const token = res.token
      const loggedUserId = res.loggedUserId
      localStorage.setItem('token', token)
      localStorage.setItem('loggedUserId', loggedUserId)
      this.router.navigate(['/'])
    }, e => {
      this.authService.errorHandler(e)
    })
  }

}
