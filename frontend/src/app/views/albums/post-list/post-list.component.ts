import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/components/post/post.model';
import { PostService } from 'src/app/components/post/post.service';
import { HeaderService } from 'src/app/components/template/header/header.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts: Post[]

  constructor(private route: ActivatedRoute, private postService: PostService, private headerService: HeaderService) { 
    headerService.headerData = {
      title: 'Albúm de publicações',
      icon: ' photo_library',
      routeUrl: ''
    }
  }

  ngOnInit(): void {
    const albumId = this.route.snapshot.paramMap.get('id');
    this.postService.readByAlbumId(albumId).subscribe(posts => {
      this.posts = posts["content"]
    })
  }

}
