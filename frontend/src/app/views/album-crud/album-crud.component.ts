import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HeaderService } from 'src/app/components/template/header/header.service';

@Component({
  selector: 'app-album-crud',
  templateUrl: './album-crud.component.html',
  styleUrls: ['./album-crud.component.css']
})
export class AlbumCrudComponent implements OnInit {

  constructor(private router: Router, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Criar novo albúm',
      icon: 'storefront',
      routeUrl: '/albums'
    }
  }

  ngOnInit(): void {
  }

  navigateToAlbumCreate(): void {
    this.router.navigate(['/albums/create'])
  }

}
