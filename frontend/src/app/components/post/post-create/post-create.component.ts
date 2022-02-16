import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from '../post.model';
import { PostService } from '../post.service';

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.css']
})
export class PostCreateComponent implements OnInit {

  url: any = "./assets/default-post.jpg"

  post: Post = {
    image: null,
    description: '',
    link: ''
  }

  constructor(private postService: PostService,
      private router: Router) { }

  ngOnInit(): void {
    
  }

  onSelectFile(teste) {
    if(teste.target.files){
      this.post.image = teste.target.files[0]
      var reader = new FileReader();
      reader.readAsDataURL(teste.target.files[0]);
      reader.onload=(event:any) => {
        this.url = reader.result;
        
      }
    }
  }

  createPost(): void {
    this.postService.create(this.post).subscribe(() => {
      this.postService.showMessage('Publicação criada!')
      this.router.navigate(['/my-posts'])
    })
  }

  cancel(): void {
    this.router.navigate(['/my-posts'])
  }

}
