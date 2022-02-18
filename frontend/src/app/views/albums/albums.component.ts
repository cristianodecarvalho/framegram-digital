import { Component, OnInit } from '@angular/core';
import { Album } from 'src/app/components/album/album.model';
import { AlbumService } from 'src/app/components/album/album.service';
import { HeaderService } from 'src/app/components/template/header/header.service';

@Component({
  selector: 'app-albums',
  templateUrl: './albums.component.html',
  styleUrls: ['./albums.component.css']
})
export class AlbumsComponent implements OnInit {

  albums: Album[]

  constructor(private albumService: AlbumService, private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Albúns',
      icon: 'photo_album',
      routeUrl: '/posts'
    }
  }

  ngOnInit(): void {
    const loggedUserId = localStorage.getItem("loggedUserId");
    this.albumService.read().subscribe(albums => {
      this.albums = albums["content"].filter(album => album.user.id != loggedUserId)
    })
  }

}
