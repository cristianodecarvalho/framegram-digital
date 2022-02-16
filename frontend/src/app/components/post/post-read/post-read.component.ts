import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AlbumDialogComponent } from 'src/app/views/albums/album-dialog/album-dialog.component';
import { AlbumService } from '../../album/album.service';
import { Post } from '../post.model';
import { PostService } from '../post.service';

@Component({
  selector: 'app-post-read',
  templateUrl: './post-read.component.html',
  styleUrls: ['./post-read.component.css']
})
export class PostReadComponent implements OnInit {

  @Input()
  myPosts: boolean;

  @Input()
  posts: Post[]

  albumId: string

  constructor(private postervice: PostService, private router: Router, private dialog: MatDialog, private albumService: AlbumService) { }

  ngOnInit(): void {
  }

  addToAlbum(postId: string): void {
    const dialogRef = this.dialog.open(AlbumDialogComponent, {
      width: '250px',
      data: {albumId : this.albumId}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.albumService.addPostToAlbum(result, postId).subscribe(() => {
          this.albumService.showMessage("Publicação adicionada ao Albúm")
        })
      }
    });
  }

  delete(postId: string): void {
    this.postervice.delete(postId).subscribe(() => {
      this.posts = this.posts.filter( post => post.id != postId)
      this.postervice.showMessage("Post excluído!")
    });
  }

  receiveComment(comment) {
    let post = this.posts.find( post => post.id == comment.post.id)
    let commentCreate = {
      id : comment.id,
      message : comment.message,
      user : comment.user
      
    }
    post["comments"].push(commentCreate)
  }

  removeComment(comment) {

    let post = this.posts.find( post => post.id == comment.post.id)
    const index = post["comments"].map( c => c.id).indexOf(comment.id)
    post["comments"].splice(index, 1)

  }
}
