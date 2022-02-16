import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HeaderService } from 'src/app/components/template/header/header.service';

@Component({
  selector: 'app-post-crud',
  templateUrl: './post-crud.component.html',
  styleUrls: ['./post-crud.component.css']
})
export class PostCrudComponent implements OnInit {

  constructor(private router: Router, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Publicações',
      icon: 'crop_original',
      routeUrl: '/posts'
    }
  }

  ngOnInit(): void {
  }

  navigateToPostCreate(): void {
    this.router.navigate(['/posts/create'])
  }

}
