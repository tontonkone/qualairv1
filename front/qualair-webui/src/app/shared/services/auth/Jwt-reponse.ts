export class JwtResponse {
    accessToken: string | undefined;
    type: string | undefined;
    username: string | undefined;
    authorities: string[] | undefined;
}
