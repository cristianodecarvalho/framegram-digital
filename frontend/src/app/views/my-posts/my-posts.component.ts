import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/components/post/post.model';
import { PostService } from 'src/app/components/post/post.service';
import { HeaderService } from 'src/app/components/template/header/header.service';

@Component({
  selector: 'app-my-posts',
  templateUrl: './my-posts.component.html',
  styleUrls: ['./my-posts.component.css']
})
export class MyPostsComponent implements OnInit {

  loggedUserId: number;
  posts: Post[]

  constructor(private router: Router, private headerService: HeaderService, private postService: PostService) {
    headerService.headerData = {
      title: 'Minhas publicações',
      icon: 'auto_stories',
      routeUrl: '/my-posts'
    }
  }

  ngOnInit(): void {
    this.loggedUserId = parseInt(localStorage.getItem("loggedUserId"))
    this.postService.readMyPosts().subscribe( posts => {
      this.posts = posts["content"]
    });
  }

  navigateToPostCreate(): void {
    this.router.navigate(['/posts/create'])
  }

}
