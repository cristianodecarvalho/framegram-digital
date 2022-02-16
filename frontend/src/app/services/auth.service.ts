import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EMPTY, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Post } from '../components/post/post.model';
import { Auth } from '../models/Auth.model';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    baseUrl = "/api/auth";

    showMenuEmitter = new EventEmitter<boolean>();

    constructor(private snackBar: MatSnackBar, private http: HttpClient) { }

    showMessage(msg: string, isError: boolean = false): void {
        this.snackBar.open(msg, "X", {
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: isError ? ["msg-error"] : ["msg-success"],
        });
    }

    authenticate(auth: Auth): Observable<any> {
        return this.http.post(this.baseUrl, auth).pipe(
            map((obj) => {
                this.showMenuEmitter.emit(true)
                return obj
            }),
            catchError((e) => this.errorHandler(e))
        );
    }

    loggout(): void {
        localStorage.clear()
        this.showMenuEmitter.emit(false)
    }

    errorHandler(e: any): Observable<any> {
        this.showMenuEmitter.emit(false)
        this.showMessage("Ocorreu um erro!", true);
        return EMPTY;
    }
}
