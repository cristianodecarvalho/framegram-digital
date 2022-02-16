import { ProductDeleteComponent } from './components/product/product-delete/product-delete.component';
import { ProductUpdateComponent } from './components/product/product-update/product-update.component';
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./views/home/home.component";
import { ProductCrudComponent } from "./views/product-crud/product-crud.component";
import { ProductCreateComponent } from './components/product/product-create/product-create.component';
import { PostCrudComponent } from './views/post-crud/post-crud.component';
import { AlbumCrudComponent } from './views/album-crud/album-crud.component';
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
    path: "products",
    component: ProductCrudComponent
  },
  {
    path: "products/create",
    component: ProductCreateComponent
  },
  {
    path: "products/update/:id",
    component: ProductUpdateComponent
  },
  {
    path: "products/delete/:id",
    component: ProductDeleteComponent
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
    path: "posts/delete/:id",
    component: ProductDeleteComponent
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
    path: "albums/delete/:id",
    component: ProductDeleteComponent
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
