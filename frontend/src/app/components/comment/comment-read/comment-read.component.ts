import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommentService } from '../comment.service';
import { CommentPost } from '../commentPost.model';

@Component({
  selector: 'app-comment-read',
  templateUrl: './comment-read.component.html',
  styleUrls: ['./comment-read.component.css']
})
export class CommentReadComponent implements OnInit {

  loggedUserId: number

  @Input()
  comments: CommentPost[]

  @Output()
  removeComment = new EventEmitter()

  constructor(private commentService: CommentService) { }

  ngOnInit(): void {
    this.loggedUserId = parseInt(localStorage.getItem("loggedUserId"))
  }

  delete(commentId): void {
    this.commentService.delete(commentId).subscribe(comment => {
      this.removeComment.emit(comment)
      this.commentService.showMessage('Comentário excluído!')
    })
  }

}
