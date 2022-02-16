import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Album } from 'src/app/components/album/album.model';
import { AlbumService } from 'src/app/components/album/album.service';
import { HeaderService } from 'src/app/components/template/header/header.service';

@Component({
  selector: 'app-my-albums',
  templateUrl: './my-albums.component.html',
  styleUrls: ['./my-albums.component.css']
})
export class MyAlbumsComponent implements OnInit {

  loggedUserId: number;
  albums: Album[]
  showSpinner: boolean = true

  constructor(private router: Router, private headerService: HeaderService, private albumService: AlbumService) {
    headerService.headerData = {
      title: 'Meus albúns',
      icon: 'photo_library',
      routeUrl: '/albums'
    }
  }

  ngOnInit(): void {
    this.loggedUserId = parseInt(localStorage.getItem("loggedUserId"))
    setTimeout( () => {
    this.showSpinner = false
    this.albumService.read().subscribe( albums => {
      this.albums = albums["content"].filter( album => album.user.id == this.loggedUserId)
    });
    },1000)
  }

  navigateToAlbumCreate(): void {
    this.router.navigate(['/albums/create'])
  }

}
