import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { CommentPost } from '../commentPost.model';
import { CommentService } from '../comment.service';
import { CommentAlbum } from '../commentAlbum.model';

@Component({
  selector: 'app-comment-create',
  templateUrl: './comment-create.component.html',
  styleUrls: ['./comment-create.component.css']
})
export class CommentCreateComponent implements OnInit {

  @Input()
  postId: string

  @Input()
  albumId: string

  @Output()
  addComment = new EventEmitter()

  message: string

  commentPost: CommentPost = {
    message: '',
    post: {
      id: ''
    }
  }

  commentAlbum: CommentAlbum = {
    message: '',
    album: {
      id: ''
    }
  }

  constructor(private commentService: CommentService, private router: Router) { }

  ngOnInit(): void {

  }

  createComment(): void {
    if(this.postId){
      this.commentPost.post.id = this.postId;
      this.commentPost.message = this.message;
      this.commentService.createCommentPost(this.commentPost).subscribe( comment => {
        this.addComment.emit(comment)
        this.message = ''
      })  
    }else{
      this.commentAlbum.album.id = this.albumId;
      this.commentAlbum.message = this.message;
      this.commentService.createCommentAlbum(this.commentAlbum).subscribe( comment => {
        this.addComment.emit(comment)
        this.message = ''
      })
    }
  }

}
