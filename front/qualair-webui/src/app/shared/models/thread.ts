import { Message } from "./message";

export interface Thread {
    id: number;
    title: string;
    messages: Message[];
  }