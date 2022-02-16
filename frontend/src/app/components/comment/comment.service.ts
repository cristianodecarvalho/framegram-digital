import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { CommentAlbum } from './commentAlbum.model';
import { CommentPost } from './commentPost.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  baseUrl = "/api/comment";

  constructor(private snackBar: MatSnackBar, private http: HttpClient) {}

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, "X", {
      duration: 3000,
      horizontalPosition: "right",
      verticalPosition: "top",
      panelClass: isError ? ["msg-error"] : ["msg-success"],
    });
  }

  createCommentPost(comment: CommentPost): Observable<CommentPost> {
    return this.http.post<CommentPost>(this.baseUrl, comment).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  createCommentAlbum(comment: CommentAlbum): Observable<CommentAlbum> {
    return this.http.post<CommentAlbum>(this.baseUrl, comment).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  read(): Observable<CommentPost[]> {
    return this.http.get<CommentPost[]>(this.baseUrl).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readById(id: number): Observable<CommentPost> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<CommentPost>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  update(comment: CommentPost): Observable<CommentPost> {
    const url = `${this.baseUrl}/${comment.id}`;
    return this.http.put<CommentPost>(url, comment).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  delete(id: string): Observable<CommentPost> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<CommentPost>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    this.showMessage("Ocorreu um erro!", true);
    return EMPTY;
  }
}
