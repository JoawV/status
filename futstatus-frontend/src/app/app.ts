import {Component, OnInit, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Club, FootballPlayer} from './components/models';
import {Player} from './components/services/player';
import {Clubs} from './components/services/clubs';

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
  clubs = signal<Club[]>([]);

  constructor(private playerService: Player, private clubService: Clubs) {}

  ngOnInit() {
    this.loadPlayers();
    this.loadClubs();
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

  loadClubs() {
    this.clubService.getClubs().subscribe({
      next: (data) => {
        console.log('Success! Setting players signal to:', data.length);
        this.clubs.set(data);
      },
      error: (error) => {
        console.error('Backend is not responding:', error);
      }
    })
  }
}
