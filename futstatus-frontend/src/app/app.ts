import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FootballPlayer } from './models';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('futstatus-frontend');

  players: FootballPlayer[] = [];

  constructor(private playerService: PlayerS) {}

  ngOnInit() {
    this.playerService.getPlayers().subscribe(data => {
      this.players = data;
    });
}
