import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./views/home/home.component";
import { PostCreateComponent } from './components/post/post-create/post-create.component';
import { AlbumCreateComponent } from './components/album/album-create/album-create.component';
import { MyPostsComponent } from './views/my-posts/my-posts.component';
import { MyAlbumsComponent } from './views/my-albums/my-albums.component';
import { AlbumsComponent } from './views/albums/albums.component';
import { PostsComponent } from './views/posts/posts.component';
import { LoginComponent } from './views/login/login.component';
import { PostUpdateComponent } from './components/post/post-update/post-update.component';
import { AlbumUpdateComponent } from './components/album/album-update/album-update.component';
import { PostListComponent } from './views/albums/post-list/post-list.component';


const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "posts",
    component: PostsComponent
  },
  {
    path: "posts/create",
    component: PostCreateComponent
  },
  {
    path: "posts/update/:id",
    component: PostUpdateComponent
  },
  {
    path: "albums",
    component: AlbumsComponent
  },
  {
    path: "albums/create",
    component: AlbumCreateComponent
  },
  {
    path: "albums/update/:id",
    component: AlbumUpdateComponent
  },
  {
    path: "albums/:id/post-list",
    component: PostListComponent
  },
  {
    path: "my-posts",
    component: MyPostsComponent
  },
  {
    path: "my-albums",
    component: MyAlbumsComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
