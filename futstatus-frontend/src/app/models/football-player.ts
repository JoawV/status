import { Club } from './club';
import { Championship } from './championship';

export interface FootballPlayer {
  id?: number;
  name: string;
  birthDate?: string;
  age?: number;
  club?: Club;
  nationality?: string;
  positions?: string;
  foot?: string;
  championshipList?: Championship[];
}
