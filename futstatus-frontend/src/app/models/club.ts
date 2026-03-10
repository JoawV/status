import { FootballPlayer } from './football-player';
import { Championship } from './championship';

export interface Club {
  id?: number;
  name: string;
  foundationDate?: string;
  stadium?: string;
  city?: string;
  footballPlayerList?: FootballPlayer[];
  championshipList?: Championship[];
}
