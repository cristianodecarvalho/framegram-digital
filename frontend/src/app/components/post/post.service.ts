import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Post } from './post.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  baseUrl = "/api/post";

  constructor(private snackBar: MatSnackBar, private http: HttpClient) {}

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, "X", {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ["msg-error"] : ["msg-success"],
    });
  }

  create(post:any): Observable<any> {
    const fd = new FormData();
    fd.append('image', post.image)
    post.image = null
    const value = JSON.stringify(post);
    const blobOverrides = new Blob([value], {
      type: 'application/json',
    });
    fd.append('post',blobOverrides);
    return this.http.post(this.baseUrl, fd)
  }

  read(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readMyPosts(): Observable<Post[]> {
    const url = `${this.baseUrl}/my-posts`;
    return this.http.get<Post[]>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readById(id: string): Observable<Post> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Post>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readByAlbumId(id: string): Observable<Post[]> {
    const url = `${this.baseUrl}/album/${id}`;
    return this.http.get<Post[]>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  update(post: Post): Observable<Post> {
    const url = `${this.baseUrl}/${post.id}`;
    return this.http.put<Post>(url, post).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  delete(id: string): Observable<Post> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage("Ocorreu um erro!", true);
    return EMPTY;
  }

   dataURItoBlob(dataURI)
{
    var byteString = atob(dataURI.split(',')[1]);

    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]

    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < byteString.length; i++)
    {
        ia[i] = byteString.charCodeAt(i);
    }

    var bb = new Blob([ab], { "type": mimeString });
    return bb;
}
}
