import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Album } from 'src/app/components/album/album.model';
import { AlbumService } from 'src/app/components/album/album.service';

@Component({
  selector: 'app-album-dialog',
  templateUrl: './album-dialog.component.html',
  styleUrls: ['./album-dialog.component.css']
})
export class AlbumDialogComponent implements OnInit {

  albums: Album[]

  constructor(
    public dialogRef: MatDialogRef<AlbumDialogComponent>,
    private albumService: AlbumService,
    @Inject(MAT_DIALOG_DATA) public albumId: string
  ) { }

  ngOnInit(): void {
    const loggedUserId = localStorage.getItem("loggedUserId");

    this.albumService.read().subscribe( albums => {
      this.albums = albums["content"].filter( album => album.user.id == loggedUserId)
    })
  }

  cancel(): void {
    this.dialogRef.close();
  }

}
