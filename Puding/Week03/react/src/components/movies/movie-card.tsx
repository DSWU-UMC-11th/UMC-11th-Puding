import { Link } from "@tanstack/react-router";
import type { Movie } from "../../types/movie";
import { cn } from "../../utils/cn";

interface MovieCardProps {
  movie: Movie;
  onToggleBookmark: (movieId: Movie["id"]) => void;
}

export function MovieCard({ movie, onToggleBookmark }: MovieCardProps) {
  const detailParams = { movieId: String(movie.id) };

  return (
    <li className="min-w-0">
      <div className="relative">
        <Link
          to="/movies/$movieId"
          params={detailParams}
          className="block overflow-hidden rounded-[9px] focus-visible:outline-3 focus-visible:outline-offset-4 focus-visible:outline-[#153cc5]"
          aria-label={`${movie.title} 상세 보기`}
        >
          <img
            className="aspect-[4/5] w-full bg-[#e6e8ed] object-cover transition-transform duration-200 hover:scale-[1.03] min-[480px]:aspect-[240/274]"
            src={movie.posterPath}
            alt={`${movie.title} 포스터`}
            width={240}
            height={274}
          />
        </Link>
        <button
          type="button"
          className={cn(
            "absolute right-2.5 top-2.5 grid h-10 w-10 place-items-center rounded-[5px] border border-white/70 text-white transition-colors focus-visible:outline-3 focus-visible:outline-offset-2 focus-visible:outline-[#153cc5] min-[480px]:h-[34px] min-[480px]:w-[34px]",
            movie.isBookmarked ? "border-[#3861e9] bg-[#3861e9] hover:bg-[#2047cd]" : "bg-[#1c1e24]/75 hover:bg-[#202a43]",
          )}
          aria-label={`${movie.title} 북마크`}
          aria-pressed={movie.isBookmarked}
          onClick={() => onToggleBookmark(movie.id)}
        >
          <img
            className="h-6 w-6 brightness-0 invert"
            src={movie.isBookmarked ? "/icons/bookmark.svg" : "/icons/bookmark-outline.svg"}
            alt=""
          />
        </button>
      </div>
      <div className="pt-[9px]">
        <h2 className="break-words text-base leading-[1.4] font-bold tracking-[-0.4px] min-[480px]:text-sm">
          <Link
            to="/movies/$movieId"
            params={detailParams}
            className="hover:underline focus-visible:rounded focus-visible:outline-3 focus-visible:outline-offset-2 focus-visible:outline-[#153cc5]"
          >
            {movie.title}
          </Link>
        </h2>
        <time className="mt-1 block text-[13px] leading-[1.4] text-[#72767e] min-[480px]:text-xs" dateTime={movie.releaseDate.replaceAll(".", "-")}>
          {movie.releaseDate}
        </time>
      </div>
    </li>
  );
}
