import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Club} from '../models';

@Injectable({
  providedIn: 'root',
})
export class Clubs {
  private apiUrl = 'http://localhost:8081/api/clubes';

  constructor(private http: HttpClient) {}

  getClubs(): Observable<Club[]> {
    return this.http.get<Club[]>(this.apiUrl);
  }
}
