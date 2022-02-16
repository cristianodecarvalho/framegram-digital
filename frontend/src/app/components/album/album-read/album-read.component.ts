import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Album } from '../album.model';
import { AlbumService } from '../album.service';

@Component({
  selector: 'app-album-read',
  templateUrl: './album-read.component.html',
  styleUrls: ['./album-read.component.css']
})
export class AlbumReadComponent implements OnInit {

  @Input()
  myAlbums: boolean;

  @Input()
  albums: Album[]

  constructor(private albumService: AlbumService, private router: Router) { }

  ngOnInit(): void {
  }

  delete(albumId: string): void {
    this.albumService.delete(albumId).subscribe(() => {
      this.albums = this.albums.filter( album => album.id != albumId)
      this.albumService.showMessage("Albúm excluída!")
    });
  }

  open(albumId: string): void {
    this.router.navigate([`/albums/${albumId}/post-list`])
  }

  receiveComment(comment) {
    let album = this.albums.find( album => album.id == comment.album.id)
    let commentCreate = {
      id : comment.id,
      message : comment.message,
      user : comment.user
      
    }
    album["comments"].push(commentCreate)
  }

  removeComment(comment) {

    let album = this.albums.find( album => album.id == comment.album.id)
    const index = album["comments"].map( c => c.id).indexOf(comment.id)
    album["comments"].splice(index, 1)

  }

}
