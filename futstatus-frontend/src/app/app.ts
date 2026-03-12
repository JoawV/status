import {Component, OnInit, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FootballPlayer} from './components/models';
import {Player} from './components/services/player';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit{
  protected readonly title = signal('futstatus-frontend');

  players = signal<FootballPlayer[]>([]);

  constructor(private playerService: Player) {}

  ngOnInit() {
    this.loadPlayers();
  }

  loadPlayers() {
    this.playerService.getPlayers().subscribe({
      next: (data) => {
        console.log('Success! Setting players signal to:', data.length);
        this.players.set(data);
      },
      error: (error) => {
        console.error('Backend is not responding:', error);
      }
    });
  }
}
