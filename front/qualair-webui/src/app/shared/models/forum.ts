import { Thread } from "./thread";
import {Message} from "./message";

export interface Forum {
    id: number;
    title: string;
    messages: Message[]
  }
