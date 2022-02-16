import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Post } from '../post/post.model';
import { Album } from './album.model';

@Injectable({
  providedIn: 'root'
})
export class AlbumService {

  baseUrl = "/api/album";

  constructor(private snackBar: MatSnackBar, private http: HttpClient) {}

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, "X", {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ["msg-error"] : ["msg-success"],
    });
  }

  create(album: Album): Observable<Album> {
    return this.http.post<Album>(this.baseUrl, album).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  read(): Observable<Album[]> {
    return this.http.get<Album[]>(this.baseUrl).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readById(id: string): Observable<Album> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Album>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  update(album: Album): Observable<Album> {
    const url = `${this.baseUrl}/${album.id}`;
    return this.http.put<Album>(url, album).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  addPostToAlbum(albumId: string, postId: string): Observable<Album> {
    const url = `${this.baseUrl}/${albumId}/post/${postId}`;
    return this.http.put(url, {}).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  delete(id: string): Observable<Album> {
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
}
