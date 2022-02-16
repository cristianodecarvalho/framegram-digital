import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Album } from '../album.model';
import { AlbumService } from '../album.service';

@Component({
  selector: 'app-album-create',
  templateUrl: './album-create.component.html',
  styleUrls: ['./album-create.component.css']
})
export class AlbumCreateComponent implements OnInit {

  album: Album = {
    description: ''    
  }

  constructor(private albumService: AlbumService,
      private router: Router) { }

  ngOnInit(): void {
    
  }

  createAlbum(): void {
    this.albumService.create(this.album).subscribe(() => {
      this.albumService.showMessage('Albúm criado!')
      this.router.navigate(['/my-albums'])
    })

  }

  cancel(): void {
    this.router.navigate(['/my-albums'])
  }

}
