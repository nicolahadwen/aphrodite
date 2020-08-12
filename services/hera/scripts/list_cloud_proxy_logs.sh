while getopts "d:f:" opt; do
  case $opt in
    d) pod=$OPTARG      ;;
    *) echo 'error' >&2
       exit 1
  esac
done

# If -d is *required*
if [ ! -d "$pod" ]; then
    echo 'Option -d missing or designates non-directory' >&2
    exit 1
fi

# If -d is *optional*
if [ -n "$dir" ] && [ ! -d "$dir" ]; then
    echo 'Option -d designates non-directory' >&2
    exit 1
fi


kubectl logs "$pod" cloud-sql-proxy