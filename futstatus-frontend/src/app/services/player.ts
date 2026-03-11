import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FootballPlayer } from '../models';

@Injectable({
  providedIn: 'root',
})
export class Player {
  private apiUrl = 'http://localhost:8081/api/jogadores';

  constructor(private http: HttpClient) { }
  
  getPlayers(): Observable<FootballPlayer[]> {
    return this.http.get<FootballPlayer[]>(this.apiUrl);
  }
}