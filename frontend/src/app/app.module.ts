import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/template/header/header.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { FooterComponent } from './components/template/footer/footer.component';
import { NavComponent } from './components/template/nav/nav.component';

import { MatSidenavModule } from  '@angular/material/sidenav';
import { MatCardModule } from  '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { HomeComponent } from './views/home/home.component';
import { MatButtonModule } from  '@angular/material/button';
import { MatSnackBarModule } from  '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule, HTTP_INTERCEPTORS } from  '@angular/common/http';

import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatDialogModule } from '@angular/material/dialog'
import { MatSelectModule } from '@angular/material/select'
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


import localePt from '@angular/common/locales/pt';
import { registerLocaleData } from  '@angular/common';
import { PostCreateComponent } from './components/post/post-create/post-create.component';
import { AlbumCreateComponent } from './components/album/album-create/album-create.component';
import { AuthInterceptor } from './http-interceptors/http.interceptor';
import { MyPostsComponent } from './views/my-posts/my-posts.component';
import { MyAlbumsComponent } from './views/my-albums/my-albums.component';
import { AlbumsComponent } from './views/albums/albums.component';
import { PostsComponent } from './views/posts/posts.component';
import { AlbumReadComponent } from './components/album/album-read/album-read.component';
import { PostReadComponent } from './components/post/post-read/post-read.component';
import { CommentCreateComponent } from './components/comment/comment-create/comment-create.component';
import { CommentReadComponent } from './components/comment/comment-read/comment-read.component';
import { LoginComponent } from './views/login/login.component';
import { PostUpdateComponent } from './components/post/post-update/post-update.component';
import { AlbumUpdateComponent } from './components/album/album-update/album-update.component';
import { AlbumDialogComponent } from './views/albums/album-dialog/album-dialog.component';
import { PostListComponent } from './views/albums/post-list/post-list.component';


registerLocaleData(localePt);

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    HomeComponent,
    PostCreateComponent,
    AlbumCreateComponent,
    MyPostsComponent,
    MyAlbumsComponent,
    AlbumsComponent,
    PostsComponent,
    AlbumReadComponent,
    PostReadComponent,
    CommentCreateComponent,
    CommentReadComponent,
    LoginComponent,
    PostUpdateComponent,
    AlbumUpdateComponent,
    AlbumDialogComponent,
    PostListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    HttpClientModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatIconModule,
    MatDialogModule,
    MatSelectModule,
    MatProgressSpinnerModule
  ],
  providers: [{
    provide: LOCALE_ID,
    useValue: 'pt-BR'
  },{
    provide: HTTP_INTERCEPTORS, 
    useClass: AuthInterceptor, 
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
