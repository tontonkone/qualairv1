// Interface pour la géométrie GeoJSON
export interface GeoJSONGeometry {
    type: string;
    coordinates:[]; 
}

// Interface pour une caractéristique GeoJSON
export interface GeoJSONFeature {
    type: string;
    geometry: GeoJSONGeometry;
    properties: {
        code: string;
        nom: string;
    };
}

// Interface pour la collection GeoJSON
export interface GeoJSONCollection {
    type: string;
    features: GeoJSONFeature[];
}
