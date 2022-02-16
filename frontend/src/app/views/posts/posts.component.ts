import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/components/post/post.model';
import { PostService } from 'src/app/components/post/post.service';
import { HeaderService } from 'src/app/components/template/header/header.service';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

  posts: Post[]
  showSpinner: boolean = true

  constructor(private postService: PostService, private headerService: HeaderService) { 
    headerService.headerData = {
      title: 'Publicações',
      icon: 'crop_original',
      routeUrl: '/posts'
    }
  }

  ngOnInit(): void {
    const loggedUserId = localStorage.getItem("loggedUserId");
    setTimeout( () => {
      this.showSpinner = false
      this.postService.read().subscribe( posts => {
        this.posts = posts["content"].filter( post => post.user.id != loggedUserId)
      })
    },1000)
  }

}
