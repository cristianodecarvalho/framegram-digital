import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../post.model';
import { PostService } from '../post.service';

@Component({
  selector: 'app-post-update',
  templateUrl: './post-update.component.html',
  styleUrls: ['./post-update.component.css']
})
export class PostUpdateComponent implements OnInit {

  post: Post

  constructor(private postService: PostService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const postId = this.route.snapshot.paramMap.get('id')
    this.postService.readById(postId).subscribe(post => {
      this.post = post
    })
  }

  updatePost(): void {
    this.postService.update(this.post).subscribe(() => {
      this.postService.showMessage('Publicação atualizada!')
      this.router.navigate(['/my-posts'])
    })
  }

  cancel(): void {
    this.router.navigate(['/my-posts'])
  }

}
