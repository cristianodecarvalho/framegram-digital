import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent {

  showMenu: boolean;

  constructor(private authService: AuthService) {

  }

  ngOnInit(){
    this.showMenu = localStorage.getItem("loggedUserId") ? true : false
    this.authService.showMenuEmitter.subscribe(
      show => this.showMenu = show
    );
  }

}
