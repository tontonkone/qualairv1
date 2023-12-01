export interface LoginResponse {
    authenticationToken: string;
    refreshToken: string;
    expiresAt: Date;
    email: string;
    firstName: String;
    lastName: String;
    id: number;
    role: string;
}
