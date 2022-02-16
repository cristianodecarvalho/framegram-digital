import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Album } from '../album.model';
import { AlbumService } from '../album.service';

@Component({
  selector: 'app-album-update',
  templateUrl: './album-update.component.html',
  styleUrls: ['./album-update.component.css']
})
export class AlbumUpdateComponent implements OnInit {

  album: Album

  constructor(private albumService: AlbumService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const albumId = this.route.snapshot.paramMap.get('id')
    this.albumService.readById(albumId).subscribe(album => {
      this.album = album
    })
  }

  updateAlbum(): void {
    this.albumService.update(this.album).subscribe(() => {
      this.albumService.showMessage('Album atualizado com sucesso!')
      this.router.navigate(['/my-albums'])
    })
  }

  cancel(): void {
    this.router.navigate(['/my-albums'])
  }

}
