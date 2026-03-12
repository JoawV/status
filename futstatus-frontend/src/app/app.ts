import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FootballPlayer } from './models';
import {Player} from './services/player';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('futstatus-frontend');

  players: FootballPlayer[] = [];

  constructor(private playerService: Player) {}

  ngOnInit() {
    this.playerService.getPlayers().subscribe({next: (data) => {
      this.players = data;
        console.log('Dados recebidos!', data);
    },
    error: (error) => {
      console.error('Erro ao buscar dados:', error);
    }
    });
  }
}
